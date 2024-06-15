import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AutorizacionDetailComponent } from './autorizacion-detail.component';

describe('Autorizacion Management Detail Component', () => {
  let comp: AutorizacionDetailComponent;
  let fixture: ComponentFixture<AutorizacionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AutorizacionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ autorizacion: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(AutorizacionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AutorizacionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load autorizacion on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.autorizacion).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
