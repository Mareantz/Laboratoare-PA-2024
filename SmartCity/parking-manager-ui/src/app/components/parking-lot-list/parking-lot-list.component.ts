import { Component, OnInit } from '@angular/core';
import { ParkingLotService } from '../../services/parking-lot.service';
import { ParkingLot } from '../../models/parking-lot';
import { CommonModule, NgForOf } from '@angular/common';

@Component({
  selector: 'app-parking-lot-list',
  standalone:true,
  imports:[CommonModule,NgForOf],
  templateUrl: './parking-lot-list.component.html',
  styleUrls: ['./parking-lot-list.component.scss']
})
export class ParkingLotListComponent implements OnInit {
  parkingLots: ParkingLot[] = [];

  constructor(private parkingLotService: ParkingLotService) { }

  ngOnInit(): void {
    this.getParkingLots();
  }

  getParkingLots(): void {
    this.parkingLotService.getParkingLots().subscribe(data => {
      this.parkingLots = data;
    });
  }

  deleteParkingLot(id: number | undefined): void {
    if (id !== undefined) {
      this.parkingLotService.deleteParkingLot(id).subscribe(() => {
        this.getParkingLots();
      });
    }
  }
}
