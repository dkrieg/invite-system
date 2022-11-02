import { Injectable } from '@angular/core';
import { environment } from './../../../environments/environment';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReserveclubService {

  constructor(private httpClient: HttpClient) { }
  
  /* public CreateReserveClub(request: any){
    return this.httpClient.post(environment.apiUrl+environment.postReserveClub,request);
  } */
  // public GetMembers(memberId: string){
  //   return this.httpClient.get(environment.apiUrl+environment.retrieveMembers+memberId);
  // }

  // public GetMembers(memberId: string){
  //   return this.httpClient.get(environment.apiUrl+environment.retrieveMembers+memberId);
  // }

  public GetMember(){
    return this.httpClient.get(environment.apiUrl+environment.getMembers);
  }

  public GetClubs(){
    return this.httpClient.get(environment.apiUrl+environment.getClub);
  }

  public postDistance(stat:string, end:string)
  {
    //return this .httpClient.post(environment.apiUrl+environment.postDistance+start+'/'+end,request);
    return this .httpClient.get(environment.apiUrl+environment.postDistance);
  }

  public postReservation(memberid:string,chosenclubId:string,amenityId:string,reservationdate:string, request: any)
  {
   // return this .httpClient.post(environment.apiUrl+environment.postReservation+memberid+'/'+amenityId+'/'+chosenclubId+'/'+reservationdate,request);
   return this .httpClient.get(environment.apiUrl+environment.postReservation+memberid,request);

  }

  public GetMemberInfo(memberID: string)
  {
    return this.httpClient.get(environment.apiUrl+environment.getMembers+'/'+memberID);
  }
 
  public GetClubInfo(clubID: string)
  {
    return this.httpClient.get(environment.apiUrl+environment.getClub+'/'+clubID);
  }

  public getMembership(memberId: string){
    return this.httpClient.get(environment.apiUrl+environment.getMembership+memberId);

  }
}
