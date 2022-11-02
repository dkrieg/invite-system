import { Component, OnInit,ViewChildren } from '@angular/core';
import { FormArray, FormBuilder, FormGroup,Validators} from '@angular/forms';
import { ReserveclubService} from './service/reserveclub.service';

import { SweetAlertOptions} from 'sweetalert2';



@Component({
  selector: 'app-reserveclub',
  templateUrl: './reserveclub.component.html',
  styleUrls: ['./reserveclub.component.scss']
})
export class ReserveclubComponent implements OnInit {
 

  reserveClubForm: FormGroup;
  @ViewChildren('tooltip') tooltips: { _results: any[]; };
  tooltipStatus: boolean;
  amenities=[{"id":0,"name":'No Amenities'}];
  benefitPkgs=[{"id":0,"description":'Benefits',"tooltip":'nothing loaded'}];
  members =  [{"id":1,"loginId":"Member1","firstName":"","lastName":""}];
  clubs =  [{"id":0,"name":"Select"}];
  selectedMemGeoLoc = '';
  selectedMemberName = '';
  selectedMemberAddress ='';
  selectedMemberHomeClub = '';
  selectedHomeClubAddress =' ';
  selectedHomeClubSegment =' ';
  selectedHomeClubGeo =' ';
  selectedReserveClub =' ';
  selectedReserveClubAddress =' ';
  selectedReserveClubSegment =' ';
  selectedReserveClubGeo =' ';
  selectedClubAmenities= [{"id":0,"name":"no Amenities"}];
  selectedAmenity = [{"id":0,"name":"no Amenity"}];
  selectedBenefitPackage= {"id":" ","name":" "};
  selectedAmenityID:string;
  selectedMemberID:string;
  selectedReserveClubID:string;
  selectedMemberAddressID:string;
  selectedReservedClubAddrID:string;
  selectedHomeClubAddrID:string;
  selectedMHRCDistance:string;
  selectedHCRCDistance:string;



 // constructor(private service:ReserveclubService) { }



  alertOpt:  SweetAlertOptions={};
   constructor(private fb: FormBuilder,private service:ReserveclubService) { }

   ngOnInit(): void {
     
    this.reserveClubForm = this.fb.group({
      memberName:[''],
      reservingClub:[''],
      distance:[''],
      totalAmenities: this.fb.array(this.amenities),
      userHomeClub: [''],
      bookingDate: [''],
      benefitPackage: this.fb.array(this.benefitPkgs),
    });
    this.service.GetMember().subscribe((data:any)=>{
      if(data){
      this.members = data;
      }
    })
    this.service.GetClubs().subscribe((data:any)=>{
      if(data){
      this.clubs = data;
      }
    })
  }
  memberSelection(event:any){
    debugger;
    var id = this.reserveClubForm.controls['memberName'].value;
    this.service.GetMember().subscribe((data:any)=>{
      if(data){
          var memData = data[id-1];
          this.selectedMemberName = memData.firstName +" "+ memData.lastName;
          this.selectedMemberAddress = memData.homeAddress.line1;
          if(memData.homeAddress.line2!=null)
          {
            this.selectedMemberAddress += '\n'+memData.homeAddress.line2;
          }
          this.selectedMemberAddress += '\n'+memData.homeAddress.city+','
                                        +memData.homeAddress.state +' '+memData.homeAddress.zipCode.postalCode;
          this.selectedMemGeoLoc = JSON.stringify(memData.homeAddress.geoLocation);
          this.selectedMemberAddressID = memData.homeAddress.id;
          this.service.getMembership(id).subscribe((data:any)=>{
            if(data){
              //check below line is required during original code merged
              data = data[id];
              this.reserveClubForm.get("userHomeClub")!.setValue(data.homeClub.name);
              this.selectedHomeClubAddress = data.homeClub.address.line1;
              this.selectedHomeClubAddrID = data.homeClub.address.id;
              if(data.homeClub.address.line2!=null)
              {
                this.selectedHomeClubAddress += '\n'+data.homeClub.address.line2;
              }
              this.selectedHomeClubAddress += '\n'+data.homeClub.address.city+','+data.homeClub.address.state 
                                            +' '+data.homeClub.address.zipCode.postalCode;
              this.selectedHomeClubGeo = "Longitude: " + data.homeClub.address.geoLocation.longitude +" Latitude: "
                                          +data.homeClub.address.geoLocation.latitude;
              this.selectedHomeClubSegment ="Color: "+ data.homeClub.segment.color +" name: "+data.homeClub.segment.color;
                           this.benefitPkgs = data.benefitPackage.benefits;
              this.benefitPkgs[0].tooltip = JSON.stringify(data.benefitPackage);
              this.selectedBenefitPackage.id = data.benefitPackage.id;
              this.selectedBenefitPackage.name = data.benefitPackage.name;
              if(this.selectedReservedClubAddrID && this.selectedMemberAddressID)
              {
                  this.service.postDistance(this.selectedReservedClubAddrID,this.selectedMemberAddressID).subscribe((response:any)=>{
                  if(response)
                  {
                    this.selectedMHRCDistance = response.miles;
                  }
              });
             }
             if(this.selectedReservedClubAddrID && this.selectedHomeClubAddrID)
              {
                  this.service.postDistance(this.selectedReservedClubAddrID,this.selectedHomeClubAddrID).subscribe((response:any)=>{
                  if(response)
                  {
                    this.selectedHCRCDistance = response.miles;
                  }
              });
             }
            }      
        });
        }
    });

  }
  clubSelection(event:any){
    debugger;
    const memberId = this.reserveClubForm.controls['memberName'].value;
    const clubId = this.reserveClubForm.controls['reservingClub'].value;
    this.service.GetClubInfo(clubId).subscribe((data:any)=>{
      if(data){
        this.amenities = data.amenities;
        this.Amenities.removeAt(0);
        
        this.amenities.map(e=>{
          return this.Amenities.push(this.fb.control({
            id: e.id,
            name:e.name
          }))
        })
        this.selectedReserveClubAddress = data.address.line1;
        this.selectedReservedClubAddrID = data.address.id;
              if(data.address.line2!=null)
              {
                this.selectedReserveClubAddress += '\n'+data.address.line2;
              }
              this.selectedReserveClubAddress += '\n'+data.address.city+','+data.address.state 
                                            +' '+data.address.zipCode.postalCode;
              this.selectedReserveClubGeo = "Longitude: " + data.address.geoLocation.longitude +" Latitude: "
                                          +data.address.geoLocation.latitude;
              this.selectedReserveClubSegment ="Color: "+ data.segment.color +" name: "+data.segment.name;
              // this.selectedMHRCDistance = 
              if(this.selectedReservedClubAddrID && this.selectedMemberAddressID)
              {
                  this.service.postDistance(this.selectedReservedClubAddrID,this.selectedMemberAddressID).subscribe((response:any)=>{
                  if(response)
                  {
                    this.selectedMHRCDistance = response.miles;
                  }
              });
             }
             if(this.selectedReservedClubAddrID && this.selectedHomeClubAddrID)
              {
                  this.service.postDistance(this.selectedReservedClubAddrID,this.selectedHomeClubAddrID).subscribe((response:any)=>{
                  if(response)
                  {
                    this.selectedHCRCDistance = response.miles;
                  }
              });
             }
      }
    });
  }
   toggleTooltips() {
    this.tooltipStatus = !this.tooltipStatus;
    if (this.tooltipStatus) {
      setTimeout(() => {
        this.tooltips._results.forEach(item => item.show());
      })
    } else {
      this.tooltips._results.forEach(item => item.hide());
    }
  }
  get Amenities(): FormArray{
    return this.reserveClubForm.get('totalAmenities') as FormArray
  }
  selectedAmenities(event:any){
    debugger;
    if(event)
    {
      this.selectedAmenityID = event.value;
    }
  }

  submitreserveClub(){
    console.log(JSON.stringify(this.reserveClubForm.value));
    this.reserveClubForm.patchValue({totalAmenities: this.amenities});
    let bps=this.benefitPkgs.map(e=>{
      return {'BPId':e.id}
    });
    debugger;
    var memberId = this.reserveClubForm.controls['memberName'].value;
    var reserveclubid = this.reserveClubForm.controls['reservingClub'].value;
    var amenityID = this.selectedAmenityID;
    var bookingDate = this.reserveClubForm.controls['bookingDate'].value;

    this.service.postReservation(memberId,reserveclubid,amenityID,bookingDate, memberId).subscribe((data:any)=>{
      if(data){
        data = data[1];
        if(!data.reservationIsApproved)
        {
        if(confirm("Reservation denied \n"+data.reservationDeclineReason)) {
          
        };
      }
        else
          alert ("Reserved");

      }
    });

   // debugger
    // this.alertOpt= {
    //   title: 'Success!',
    //   text: 'Saved successfully',
    //   toast: false,x``
    //   allowOutsideClick: false,
    // }
    
   // alert("OverAllResult - Denied  \n stepResults \n 4RoundsPerMonthPerCommunity - Accepted \n ");
  }

}

