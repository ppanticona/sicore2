import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DetallePrecuentaDetailComponent } from './detalle-precuenta-detail.component';

describe('DetallePrecuenta Management Detail Component', () => {
  let comp: DetallePrecuentaDetailComponent;
  let fixture: ComponentFixture<DetallePrecuentaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetallePrecuentaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ detallePrecuenta: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(DetallePrecuentaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DetallePrecuentaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load detallePrecuenta on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.detallePrecuenta).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
