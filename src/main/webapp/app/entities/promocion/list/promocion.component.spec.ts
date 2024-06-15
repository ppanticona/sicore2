import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { PromocionService } from '../service/promocion.service';

import { PromocionComponent } from './promocion.component';

describe('Promocion Management Component', () => {
  let comp: PromocionComponent;
  let fixture: ComponentFixture<PromocionComponent>;
  let service: PromocionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'promocion', component: PromocionComponent }]), HttpClientTestingModule],
      declarations: [PromocionComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(PromocionComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PromocionComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(PromocionService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 'ABC' }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.promocions?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to promocionService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getPromocionIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getPromocionIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
