import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtmDisplayComponent } from './atm-display.component';

describe('AtmDisplayComponent', () => {
  let component: AtmDisplayComponent;
  let fixture: ComponentFixture<AtmDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtmDisplayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AtmDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
