import { Component, NgZone, OnDestroy, OnInit } from '@angular/core';
import {Measurement} from "./measurement";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ApicallService} from "../apicall.service";
import { Observable, Subscription } from 'rxjs';
import * as $ from 'jquery';
import * as CanvasJS from '../../canvasjs.min';

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

  constructor(public http: HttpClient, private apiService: ApicallService, private zone: NgZone) { }

  temperaturePoints = [];
  humidityPoints = [];
  temperatureChart;
  humidityChart;
  temperatureDpsLength = 0;
  humidityDpsLength = 0;

  ngOnInit(): void {
    this.temperatureChart = new CanvasJS.Chart("temperatureChart",{
      exportEnabled: true,
      title:{
        text:"Temperature"
      },
      data: [{
        type: "spline",
        dataPoints : this.temperaturePoints,
      }]
    });
    this.humidityChart = new CanvasJS.Chart("humidityChart",{
      exportEnabled: true,
      title:{
        text:"Humidity"
      },
      data: [{
        type: "spline",
        dataPoints : this.humidityPoints,
      }]
    });
  }

  clickedMarker(lat: number, lng: number) {
    console.log(`clicked the marker: ${lat} | ${lng}`)
    this.getMeasurements(`${lat},${lng}`).subscribe({
      next: data => {
        let jdata: Measurement = JSON.parse(data);
        console.log(jdata);
        this.updateTemperatureChart(jdata.temperature, jdata.id);
        this.updateHumidityChart(jdata.humidity, jdata.id);
      },
      error: err => console.error(err)
    });
  }

  updateTemperatureChart(temperature: number, measurementId: string) {

    if (this.temperaturePoints.length > 20) {
      this.temperaturePoints.shift();
    }
    this.temperaturePoints.push({
      x: this.temperatureDpsLength,
      y: temperature
    });
    this.temperatureDpsLength++;
    this.temperatureChart.render();
  }

  updateHumidityChart(humidity: number, measurementId: string) {

    if (this.humidityPoints.length > 20) {
      this.humidityPoints.shift();
    }
    this.humidityPoints.push({
      x: this.humidityDpsLength,
      y: humidity
    });
    this.humidityDpsLength++;
    this.humidityChart.render();
  }

  getMeasurements(location: string): Observable<string> {
    return Observable.create(
      observer => {
        let source = new EventSource("http://localhost:8081/stream/measurements?location=" + location);
        source.onmessage = event => {
          this.zone.run(() => {
            observer.next(event.data)
          })
        }
        source.onerror = event => {
          this.zone.run(() => {
            observer.error(event)
          })
        }
      }
    )
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
