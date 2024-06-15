import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MovimientoCajaDetailComponent } from './movimiento-caja-detail.component';

describe('MovimientoCaja Management Detail Component', () => {
  let comp: MovimientoCajaDetailComponent;
  let fixture: ComponentFixture<MovimientoCajaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovimientoCajaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ movimientoCaja: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(MovimientoCajaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(MovimientoCajaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load movimientoCaja on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.movimientoCaja).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
