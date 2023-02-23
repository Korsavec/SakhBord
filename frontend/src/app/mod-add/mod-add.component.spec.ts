import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModAddComponent} from './mod-add.component';

describe('ModAddComponent', () => {
  let component: ModAddComponent;
  let fixture: ComponentFixture<ModAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
