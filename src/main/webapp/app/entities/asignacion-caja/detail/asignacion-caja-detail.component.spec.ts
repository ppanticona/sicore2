import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AsignacionCajaDetailComponent } from './asignacion-caja-detail.component';

describe('AsignacionCaja Management Detail Component', () => {
  let comp: AsignacionCajaDetailComponent;
  let fixture: ComponentFixture<AsignacionCajaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AsignacionCajaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ asignacionCaja: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(AsignacionCajaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AsignacionCajaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load asignacionCaja on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.asignacionCaja).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
