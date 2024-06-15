import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISecuencias } from '../secuencias.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../secuencias.test-samples';

import { SecuenciasService } from './secuencias.service';

const requireRestSample: ISecuencias = {
  ...sampleWithRequiredData,
};

describe('Secuencias Service', () => {
  let service: SecuenciasService;
  let httpMock: HttpTestingController;
  let expectedResult: ISecuencias | ISecuencias[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SecuenciasService);
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

    it('should create a Secuencias', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const secuencias = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(secuencias).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Secuencias', () => {
      const secuencias = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(secuencias).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Secuencias', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Secuencias', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Secuencias', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSecuenciasToCollectionIfMissing', () => {
      it('should add a Secuencias to an empty array', () => {
        const secuencias: ISecuencias = sampleWithRequiredData;
        expectedResult = service.addSecuenciasToCollectionIfMissing([], secuencias);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(secuencias);
      });

      it('should not add a Secuencias to an array that contains it', () => {
        const secuencias: ISecuencias = sampleWithRequiredData;
        const secuenciasCollection: ISecuencias[] = [
          {
            ...secuencias,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSecuenciasToCollectionIfMissing(secuenciasCollection, secuencias);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Secuencias to an array that doesn't contain it", () => {
        const secuencias: ISecuencias = sampleWithRequiredData;
        const secuenciasCollection: ISecuencias[] = [sampleWithPartialData];
        expectedResult = service.addSecuenciasToCollectionIfMissing(secuenciasCollection, secuencias);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(secuencias);
      });

      it('should add only unique Secuencias to an array', () => {
        const secuenciasArray: ISecuencias[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const secuenciasCollection: ISecuencias[] = [sampleWithRequiredData];
        expectedResult = service.addSecuenciasToCollectionIfMissing(secuenciasCollection, ...secuenciasArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const secuencias: ISecuencias = sampleWithRequiredData;
        const secuencias2: ISecuencias = sampleWithPartialData;
        expectedResult = service.addSecuenciasToCollectionIfMissing([], secuencias, secuencias2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(secuencias);
        expect(expectedResult).toContain(secuencias2);
      });

      it('should accept null and undefined values', () => {
        const secuencias: ISecuencias = sampleWithRequiredData;
        expectedResult = service.addSecuenciasToCollectionIfMissing([], null, secuencias, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(secuencias);
      });

      it('should return initial array if no Secuencias is added', () => {
        const secuenciasCollection: ISecuencias[] = [sampleWithRequiredData];
        expectedResult = service.addSecuenciasToCollectionIfMissing(secuenciasCollection, undefined, null);
        expect(expectedResult).toEqual(secuenciasCollection);
      });
    });

    describe('compareSecuencias', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSecuencias(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSecuencias(entity1, entity2);
        const compareResult2 = service.compareSecuencias(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSecuencias(entity1, entity2);
        const compareResult2 = service.compareSecuencias(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSecuencias(entity1, entity2);
        const compareResult2 = service.compareSecuencias(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
