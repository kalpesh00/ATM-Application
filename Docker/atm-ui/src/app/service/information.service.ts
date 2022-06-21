import { Injectable } from '@angular/core';
import {HttpClient ,HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InformationService {

  constructor(private http:HttpClient) { }

  getBalance(data:any) {
    return this.http.post(environment.baseUrl + "checkBalance", data);
  }

  withdrawAmount(data:any, amount: string) {
    return this.http.post(environment.baseUrl + "withdrawMoney" + "?amount=" + amount, data);
  }
}
