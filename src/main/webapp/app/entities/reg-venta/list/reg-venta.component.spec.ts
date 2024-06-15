import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { RegVentaService } from '../service/reg-venta.service';

import { RegVentaComponent } from './reg-venta.component';

describe('RegVenta Management Component', () => {
  let comp: RegVentaComponent;
  let fixture: ComponentFixture<RegVentaComponent>;
  let service: RegVentaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'reg-venta', component: RegVentaComponent }]), HttpClientTestingModule],
      declarations: [RegVentaComponent],
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
      .overrideTemplate(RegVentaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RegVentaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(RegVentaService);

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
    expect(comp.regVentas?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to regVentaService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getRegVentaIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getRegVentaIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
