import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EmpleadosDetailComponent } from './empleados-detail.component';

describe('Empleados Management Detail Component', () => {
  let comp: EmpleadosDetailComponent;
  let fixture: ComponentFixture<EmpleadosDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmpleadosDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ empleados: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(EmpleadosDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(EmpleadosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load empleados on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.empleados).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
