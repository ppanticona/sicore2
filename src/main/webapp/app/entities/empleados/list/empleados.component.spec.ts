import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { EmpleadosService } from '../service/empleados.service';

import { EmpleadosComponent } from './empleados.component';

describe('Empleados Management Component', () => {
  let comp: EmpleadosComponent;
  let fixture: ComponentFixture<EmpleadosComponent>;
  let service: EmpleadosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'empleados', component: EmpleadosComponent }]), HttpClientTestingModule],
      declarations: [EmpleadosComponent],
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
      .overrideTemplate(EmpleadosComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EmpleadosComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(EmpleadosService);

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
    expect(comp.empleados?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to empleadosService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getEmpleadosIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getEmpleadosIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
