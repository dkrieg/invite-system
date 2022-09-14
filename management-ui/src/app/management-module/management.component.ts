import { Component, NgModule, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../shared.service';
@Component({
  selector: 'management',
  templateUrl: './management.component.html',
  styleUrls: [],
})
export class ManagementComponent implements OnInit {
  public taskId: string | undefined;
  public formScreen:string | undefined ;
  constructor( public sharedService:SharedService, private router:Router) { }

  ngOnInit(): void {
    if(!this.sharedService.directAccess){
        this.router.navigate([""])

    }
    this.fetchTaskId();
  }

  fetchTaskId() {
    const url = 'assets/tasklist.json';
    this.sharedService.fetchTaskandClaim(url).subscribe(
      (data: any) => (this.taskId = data?.taskId),
      (error) => console.log('error ==> ', error)
    );
  }



  claimTask(){
  let url = 'assets/claim.json?/'+ this.taskId;
    this.sharedService.fetchTaskandClaim(url).subscribe(
      (data: any) => (this.formScreen = data["form-key"]),
      (error) => console.log('error ==> ', error)
    );
    
}

}


