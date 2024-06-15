import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { DetalleOrdenService } from '../service/detalle-orden.service';

import { DetalleOrdenComponent } from './detalle-orden.component';

describe('DetalleOrden Management Component', () => {
  let comp: DetalleOrdenComponent;
  let fixture: ComponentFixture<DetalleOrdenComponent>;
  let service: DetalleOrdenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'detalle-orden', component: DetalleOrdenComponent }]), HttpClientTestingModule],
      declarations: [DetalleOrdenComponent],
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
      .overrideTemplate(DetalleOrdenComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DetalleOrdenComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DetalleOrdenService);

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
    expect(comp.detalleOrdens?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to detalleOrdenService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getDetalleOrdenIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getDetalleOrdenIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
