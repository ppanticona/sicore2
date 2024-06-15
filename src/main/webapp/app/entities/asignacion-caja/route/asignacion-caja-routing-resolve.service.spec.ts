import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IAsignacionCaja } from '../asignacion-caja.model';
import { AsignacionCajaService } from '../service/asignacion-caja.service';

import { AsignacionCajaRoutingResolveService } from './asignacion-caja-routing-resolve.service';

describe('AsignacionCaja routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: AsignacionCajaRoutingResolveService;
  let service: AsignacionCajaService;
  let resultAsignacionCaja: IAsignacionCaja | null | undefined;

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
    routingResolveService = TestBed.inject(AsignacionCajaRoutingResolveService);
    service = TestBed.inject(AsignacionCajaService);
    resultAsignacionCaja = undefined;
  });

  describe('resolve', () => {
    it('should return IAsignacionCaja returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAsignacionCaja = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultAsignacionCaja).toEqual({ id: 'ABC' });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAsignacionCaja = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAsignacionCaja).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IAsignacionCaja>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAsignacionCaja = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultAsignacionCaja).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
