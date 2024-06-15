import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AsignacionCajaService } from '../service/asignacion-caja.service';

import { AsignacionCajaComponent } from './asignacion-caja.component';

describe('AsignacionCaja Management Component', () => {
  let comp: AsignacionCajaComponent;
  let fixture: ComponentFixture<AsignacionCajaComponent>;
  let service: AsignacionCajaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'asignacion-caja', component: AsignacionCajaComponent }]), HttpClientTestingModule],
      declarations: [AsignacionCajaComponent],
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
      .overrideTemplate(AsignacionCajaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AsignacionCajaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AsignacionCajaService);

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
    expect(comp.asignacionCajas?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to asignacionCajaService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getAsignacionCajaIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAsignacionCajaIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
