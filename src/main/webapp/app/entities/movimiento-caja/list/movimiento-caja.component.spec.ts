import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { MovimientoCajaService } from '../service/movimiento-caja.service';

import { MovimientoCajaComponent } from './movimiento-caja.component';

describe('MovimientoCaja Management Component', () => {
  let comp: MovimientoCajaComponent;
  let fixture: ComponentFixture<MovimientoCajaComponent>;
  let service: MovimientoCajaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'movimiento-caja', component: MovimientoCajaComponent }]), HttpClientTestingModule],
      declarations: [MovimientoCajaComponent],
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
      .overrideTemplate(MovimientoCajaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MovimientoCajaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(MovimientoCajaService);

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
    expect(comp.movimientoCajas?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to movimientoCajaService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getMovimientoCajaIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getMovimientoCajaIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
