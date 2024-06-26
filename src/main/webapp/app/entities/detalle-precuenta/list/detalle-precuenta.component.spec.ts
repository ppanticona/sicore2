import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { DetallePrecuentaService } from '../service/detalle-precuenta.service';

import { DetallePrecuentaComponent } from './detalle-precuenta.component';

describe('DetallePrecuenta Management Component', () => {
  let comp: DetallePrecuentaComponent;
  let fixture: ComponentFixture<DetallePrecuentaComponent>;
  let service: DetallePrecuentaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'detalle-precuenta', component: DetallePrecuentaComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [DetallePrecuentaComponent],
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
      .overrideTemplate(DetallePrecuentaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DetallePrecuentaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DetallePrecuentaService);

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
    expect(comp.detallePrecuentas?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to detallePrecuentaService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getDetallePrecuentaIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getDetallePrecuentaIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
