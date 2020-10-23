import { Component, OnInit } from '@angular/core';
import {Measurement} from "./measurement";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ApicallService} from "../apicall.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  measurement: Measurement[];

  zoom = 15;
  lat = 50.5124138;
  lng = 30.7913499;

  constructor(public http: HttpClient, private apiService: ApicallService) { }

  ngOnInit(): void {
  }

  clickedMarker(lat: number, lng: number) {
    this.getMeasurements();
    console.log(`clicked the marker: ${lat} | ${lng}`)
  }

  getMeasurements() {
    this.apiService
      .getUsers()
      .subscribe((data:any) => {
        this.measurement = data;
        console.log("Measurement---> " + this.measurement.length);
      });
  }

  markers: marker[] = [
    {
      lat: 50.5124138,
      lng: 30.7913499,
    }
  ]

}

interface marker {
  lat: number;
  lng: number;
}
