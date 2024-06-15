import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SecuenciasDetailComponent } from './secuencias-detail.component';

describe('Secuencias Management Detail Component', () => {
  let comp: SecuenciasDetailComponent;
  let fixture: ComponentFixture<SecuenciasDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SecuenciasDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ secuencias: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(SecuenciasDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SecuenciasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load secuencias on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.secuencias).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
