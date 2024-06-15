import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AsistenciaService } from '../service/asistencia.service';

import { AsistenciaComponent } from './asistencia.component';

describe('Asistencia Management Component', () => {
  let comp: AsistenciaComponent;
  let fixture: ComponentFixture<AsistenciaComponent>;
  let service: AsistenciaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'asistencia', component: AsistenciaComponent }]), HttpClientTestingModule],
      declarations: [AsistenciaComponent],
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
      .overrideTemplate(AsistenciaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AsistenciaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AsistenciaService);

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
    expect(comp.asistencias?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to asistenciaService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getAsistenciaIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAsistenciaIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
