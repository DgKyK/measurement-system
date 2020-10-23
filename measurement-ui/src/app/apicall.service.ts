import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Measurement} from "./home/measurement";
import { from, Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ApicallService {
  constructor(private httpClient: HttpClient) {}
  getUsers() {
    return this.httpClient.get(`http://localhost:8083/keeper/measurements`).
    pipe(
      map((data: Measurement[]) => {
        return data;
      }), catchError( error => {
        return throwError( 'Something went wrong!' );
      })
    )
  }
}
