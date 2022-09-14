import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SharedService {
  public directAccess:boolean =false;
  constructor(private http: HttpClient) {}

  fetchSideMenuData<T, R>(url: string) {
    return this.http.get<T>(url);
  }

  fetchTaskandClaim<T,R>(url:string){
    return this.http.get<T>(url);
  }
}
