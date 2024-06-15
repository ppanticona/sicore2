import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IMovimientoCaja } from '../movimiento-caja.model';
import { MovimientoCajaService } from '../service/movimiento-caja.service';

import { MovimientoCajaRoutingResolveService } from './movimiento-caja-routing-resolve.service';

describe('MovimientoCaja routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: MovimientoCajaRoutingResolveService;
  let service: MovimientoCajaService;
  let resultMovimientoCaja: IMovimientoCaja | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(MovimientoCajaRoutingResolveService);
    service = TestBed.inject(MovimientoCajaService);
    resultMovimientoCaja = undefined;
  });

  describe('resolve', () => {
    it('should return IMovimientoCaja returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultMovimientoCaja = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultMovimientoCaja).toEqual({ id: 'ABC' });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultMovimientoCaja = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultMovimientoCaja).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IMovimientoCaja>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultMovimientoCaja = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultMovimientoCaja).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
