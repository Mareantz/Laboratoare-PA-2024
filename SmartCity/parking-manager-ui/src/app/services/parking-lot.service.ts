import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ParkingLot } from '../models/parking-lot';

@Injectable({
  providedIn: 'root'
})
export class ParkingLotService {

  private apiUrl = 'http://localhost:8081/api/parking-lots';

  constructor(private http: HttpClient) { }

  getParkingLots(): Observable<ParkingLot[]> {
    return this.http.get<ParkingLot[]>(this.apiUrl);
  }

  getParkingLotById(id: number): Observable<ParkingLot> {
    return this.http.get<ParkingLot>(`${this.apiUrl}/${id}`);
  }

  createParkingLot(parkingLot: ParkingLot): Observable<ParkingLot> {
    return this.http.post<ParkingLot>(this.apiUrl, parkingLot);
  }

  updateParkingLot(parkingLot: ParkingLot): Observable<ParkingLot> {
    return this.http.put<ParkingLot>(`${this.apiUrl}/${parkingLot.lotId}`, parkingLot);
  }

  deleteParkingLot(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
