import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IParametros } from '../parametros.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../parametros.test-samples';

import { ParametrosService } from './parametros.service';

const requireRestSample: IParametros = {
  ...sampleWithRequiredData,
};

describe('Parametros Service', () => {
  let service: ParametrosService;
  let httpMock: HttpTestingController;
  let expectedResult: IParametros | IParametros[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ParametrosService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Parametros', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const parametros = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(parametros).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Parametros', () => {
      const parametros = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(parametros).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Parametros', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Parametros', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Parametros', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addParametrosToCollectionIfMissing', () => {
      it('should add a Parametros to an empty array', () => {
        const parametros: IParametros = sampleWithRequiredData;
        expectedResult = service.addParametrosToCollectionIfMissing([], parametros);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(parametros);
      });

      it('should not add a Parametros to an array that contains it', () => {
        const parametros: IParametros = sampleWithRequiredData;
        const parametrosCollection: IParametros[] = [
          {
            ...parametros,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addParametrosToCollectionIfMissing(parametrosCollection, parametros);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Parametros to an array that doesn't contain it", () => {
        const parametros: IParametros = sampleWithRequiredData;
        const parametrosCollection: IParametros[] = [sampleWithPartialData];
        expectedResult = service.addParametrosToCollectionIfMissing(parametrosCollection, parametros);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(parametros);
      });

      it('should add only unique Parametros to an array', () => {
        const parametrosArray: IParametros[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const parametrosCollection: IParametros[] = [sampleWithRequiredData];
        expectedResult = service.addParametrosToCollectionIfMissing(parametrosCollection, ...parametrosArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const parametros: IParametros = sampleWithRequiredData;
        const parametros2: IParametros = sampleWithPartialData;
        expectedResult = service.addParametrosToCollectionIfMissing([], parametros, parametros2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(parametros);
        expect(expectedResult).toContain(parametros2);
      });

      it('should accept null and undefined values', () => {
        const parametros: IParametros = sampleWithRequiredData;
        expectedResult = service.addParametrosToCollectionIfMissing([], null, parametros, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(parametros);
      });

      it('should return initial array if no Parametros is added', () => {
        const parametrosCollection: IParametros[] = [sampleWithRequiredData];
        expectedResult = service.addParametrosToCollectionIfMissing(parametrosCollection, undefined, null);
        expect(expectedResult).toEqual(parametrosCollection);
      });
    });

    describe('compareParametros', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareParametros(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareParametros(entity1, entity2);
        const compareResult2 = service.compareParametros(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareParametros(entity1, entity2);
        const compareResult2 = service.compareParametros(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareParametros(entity1, entity2);
        const compareResult2 = service.compareParametros(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
