import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RegComprasDetailComponent } from './reg-compras-detail.component';

describe('RegCompras Management Detail Component', () => {
  let comp: RegComprasDetailComponent;
  let fixture: ComponentFixture<RegComprasDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegComprasDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ regCompras: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(RegComprasDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(RegComprasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load regCompras on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.regCompras).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
