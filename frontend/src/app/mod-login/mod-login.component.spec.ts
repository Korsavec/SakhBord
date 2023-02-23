import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModLoginComponent} from './mod-login.component';

describe('ModLoginComponent', () => {
  let component: ModLoginComponent;
  let fixture: ComponentFixture<ModLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
