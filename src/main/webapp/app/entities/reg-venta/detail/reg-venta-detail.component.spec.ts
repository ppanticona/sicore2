import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RegVentaDetailComponent } from './reg-venta-detail.component';

describe('RegVenta Management Detail Component', () => {
  let comp: RegVentaDetailComponent;
  let fixture: ComponentFixture<RegVentaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegVentaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ regVenta: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(RegVentaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(RegVentaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load regVenta on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.regVenta).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
