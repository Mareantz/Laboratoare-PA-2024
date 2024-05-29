import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingLotFormComponent } from './parking-lot-form.component';

describe('ParkingLotFormComponent', () => {
  let component: ParkingLotFormComponent;
  let fixture: ComponentFixture<ParkingLotFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ParkingLotFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ParkingLotFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
