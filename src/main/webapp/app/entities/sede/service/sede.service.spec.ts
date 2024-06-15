import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISede } from '../sede.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../sede.test-samples';

import { SedeService, RestSede } from './sede.service';

const requireRestSample: RestSede = {
  ...sampleWithRequiredData,
  fecAper: sampleWithRequiredData.fecAper?.toJSON(),
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('Sede Service', () => {
  let service: SedeService;
  let httpMock: HttpTestingController;
  let expectedResult: ISede | ISede[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SedeService);
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

    it('should create a Sede', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sede = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sede).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Sede', () => {
      const sede = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sede).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Sede', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Sede', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Sede', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSedeToCollectionIfMissing', () => {
      it('should add a Sede to an empty array', () => {
        const sede: ISede = sampleWithRequiredData;
        expectedResult = service.addSedeToCollectionIfMissing([], sede);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sede);
      });

      it('should not add a Sede to an array that contains it', () => {
        const sede: ISede = sampleWithRequiredData;
        const sedeCollection: ISede[] = [
          {
            ...sede,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSedeToCollectionIfMissing(sedeCollection, sede);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Sede to an array that doesn't contain it", () => {
        const sede: ISede = sampleWithRequiredData;
        const sedeCollection: ISede[] = [sampleWithPartialData];
        expectedResult = service.addSedeToCollectionIfMissing(sedeCollection, sede);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sede);
      });

      it('should add only unique Sede to an array', () => {
        const sedeArray: ISede[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sedeCollection: ISede[] = [sampleWithRequiredData];
        expectedResult = service.addSedeToCollectionIfMissing(sedeCollection, ...sedeArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sede: ISede = sampleWithRequiredData;
        const sede2: ISede = sampleWithPartialData;
        expectedResult = service.addSedeToCollectionIfMissing([], sede, sede2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sede);
        expect(expectedResult).toContain(sede2);
      });

      it('should accept null and undefined values', () => {
        const sede: ISede = sampleWithRequiredData;
        expectedResult = service.addSedeToCollectionIfMissing([], null, sede, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sede);
      });

      it('should return initial array if no Sede is added', () => {
        const sedeCollection: ISede[] = [sampleWithRequiredData];
        expectedResult = service.addSedeToCollectionIfMissing(sedeCollection, undefined, null);
        expect(expectedResult).toEqual(sedeCollection);
      });
    });

    describe('compareSede', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSede(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareSede(entity1, entity2);
        const compareResult2 = service.compareSede(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareSede(entity1, entity2);
        const compareResult2 = service.compareSede(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareSede(entity1, entity2);
        const compareResult2 = service.compareSede(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
