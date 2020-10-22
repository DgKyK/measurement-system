import { Component, OnInit } from '@angular/core';
import {Measurement} from "./measurement";
import * as Rx from "rxjs/Rx";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ApicallService} from "../apicall.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  measurement: Measurement[];

  constructor(public http: HttpClient, private apiService: ApicallService) { }

  ngOnInit(): void {
    this.getMeasurements();
  }

  getMeasurements() {
    this.apiService
      .getUsers()
      .subscribe((data:any) => {
        this.measurement = data;
        console.log("Measurement---> " + this.measurement.length);
      });
  }

  lat = 50.5124138;
  lng = 30.7913499;

}
