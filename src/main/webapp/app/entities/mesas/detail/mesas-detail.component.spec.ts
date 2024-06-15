import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MesasDetailComponent } from './mesas-detail.component';

describe('Mesas Management Detail Component', () => {
  let comp: MesasDetailComponent;
  let fixture: ComponentFixture<MesasDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MesasDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ mesas: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(MesasDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(MesasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load mesas on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.mesas).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
