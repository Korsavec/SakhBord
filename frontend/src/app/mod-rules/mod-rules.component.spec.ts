import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ModRulesComponent} from './mod-rules.component';

describe('ModRulesComponent', () => {
  let component: ModRulesComponent;
  let fixture: ComponentFixture<ModRulesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModRulesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
