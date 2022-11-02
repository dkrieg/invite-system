import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveclubComponent } from './reserveclub.component';


describe('ReserveclubComponent', () => {
  let component: ReserveclubComponent;
  let fixture: ComponentFixture<ReserveclubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReserveclubComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReserveclubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
