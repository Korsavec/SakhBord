import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModErrorComponent} from './mod-error.component';

describe('ModErrorComponent', () => {
  let component: ModErrorComponent;
  let fixture: ComponentFixture<ModErrorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModErrorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
