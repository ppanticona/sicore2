import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ParametrosService } from '../service/parametros.service';

import { ParametrosComponent } from './parametros.component';

describe('Parametros Management Component', () => {
  let comp: ParametrosComponent;
  let fixture: ComponentFixture<ParametrosComponent>;
  let service: ParametrosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'parametros', component: ParametrosComponent }]), HttpClientTestingModule],
      declarations: [ParametrosComponent],
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
      .overrideTemplate(ParametrosComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ParametrosComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ParametrosService);

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
    expect(comp.parametros?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to parametrosService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getParametrosIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getParametrosIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
