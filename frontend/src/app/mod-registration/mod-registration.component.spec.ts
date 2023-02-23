import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModRegistrationComponent} from './mod-registration.component';

describe('ModRegistrationComponent', () => {
  let component: ModRegistrationComponent;
  let fixture: ComponentFixture<ModRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModRegistrationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
