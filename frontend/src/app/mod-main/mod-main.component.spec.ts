import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModMainComponent} from './mod-main.component';

describe('ModMainComponent', () => {
  let component: ModMainComponent;
  let fixture: ComponentFixture<ModMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
