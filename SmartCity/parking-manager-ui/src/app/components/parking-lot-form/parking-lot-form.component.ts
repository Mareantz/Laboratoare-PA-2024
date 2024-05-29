import { Component } from '@angular/core';
import { ParkingLotService } from '../../services/parking-lot.service';
import { ParkingLot } from '../../models/parking-lot';
import { Router } from '@angular/router';

@Component({
  selector: 'app-parking-lot-form',
  templateUrl: './parking-lot-form.component.html',
  styleUrls: ['./parking-lot-form.component.scss']
})
export class ParkingLotFormComponent {
  parkingLot: ParkingLot = { name: '', address: '', capacity: 0, availableSpaces: 0 };

  constructor(private parkingLotService: ParkingLotService, private router: Router) { }

  createParkingLot(): void {
    this.parkingLot.availableSpaces = this.parkingLot.capacity;  // Set availableSpaces to capacity
    this.parkingLotService.createParkingLot(this.parkingLot).subscribe(() => {
      this.router.navigate(['/parking-lots']);
    });
  }
}
