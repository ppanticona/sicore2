import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { PrecuentaService } from '../service/precuenta.service';

import { PrecuentaComponent } from './precuenta.component';

describe('Precuenta Management Component', () => {
  let comp: PrecuentaComponent;
  let fixture: ComponentFixture<PrecuentaComponent>;
  let service: PrecuentaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'precuenta', component: PrecuentaComponent }]), HttpClientTestingModule],
      declarations: [PrecuentaComponent],
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
      .overrideTemplate(PrecuentaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PrecuentaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(PrecuentaService);

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
    expect(comp.precuentas?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to precuentaService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getPrecuentaIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getPrecuentaIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
