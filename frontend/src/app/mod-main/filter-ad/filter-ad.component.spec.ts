import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FilterAdComponent} from './filter-ad.component';

describe('FilterAdComponent', () => {
  let component: FilterAdComponent;
  let fixture: ComponentFixture<FilterAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilterAdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilterAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
