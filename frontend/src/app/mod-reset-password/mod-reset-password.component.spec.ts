import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModResetPasswordComponent } from './mod-reset-password.component';

describe('ModResetPasswordComponent', () => {
  let component: ModResetPasswordComponent;
  let fixture: ComponentFixture<ModResetPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModResetPasswordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModResetPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
