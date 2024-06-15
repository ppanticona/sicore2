import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDetallePrecuenta } from '../detalle-precuenta.model';
import { DetallePrecuentaService } from '../service/detalle-precuenta.service';

import { DetallePrecuentaRoutingResolveService } from './detalle-precuenta-routing-resolve.service';

describe('DetallePrecuenta routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DetallePrecuentaRoutingResolveService;
  let service: DetallePrecuentaService;
  let resultDetallePrecuenta: IDetallePrecuenta | null | undefined;

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
    routingResolveService = TestBed.inject(DetallePrecuentaRoutingResolveService);
    service = TestBed.inject(DetallePrecuentaService);
    resultDetallePrecuenta = undefined;
  });

  describe('resolve', () => {
    it('should return IDetallePrecuenta returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDetallePrecuenta = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultDetallePrecuenta).toEqual({ id: 'ABC' });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDetallePrecuenta = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDetallePrecuenta).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IDetallePrecuenta>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDetallePrecuenta = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultDetallePrecuenta).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
