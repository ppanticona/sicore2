import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ParametrosDetailComponent } from './parametros-detail.component';

describe('Parametros Management Detail Component', () => {
  let comp: ParametrosDetailComponent;
  let fixture: ComponentFixture<ParametrosDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ParametrosDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ parametros: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(ParametrosDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ParametrosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load parametros on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.parametros).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
