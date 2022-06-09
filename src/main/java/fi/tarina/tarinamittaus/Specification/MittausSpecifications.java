package fi.tarina.tarinamittaus.Specification;

import fi.tarina.tarinamittaus.Model.Mittaus;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Predicate;

public class MittausSpecifications {

    /** if keyword == null then specification is ignored */
    public static Specification<Mittaus> mittausKeywordLike(String keyword) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                keyword == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("mittaus_asianhallinta_id"), keyword),
                        criteriaBuilder.like(root.get("pdf_raportin_linkki"), keyword),
                        criteriaBuilder.like(root.get("perustamistapa"), keyword),
                        criteriaBuilder.like(root.get("julkisivumateriaali"), keyword),
                        criteriaBuilder.like(root.get("runkomateriaali"), keyword),
                        criteriaBuilder.like(root.get("katuosoite"), keyword),
                        criteriaBuilder.like(root.get("postinumero"), keyword),
                        criteriaBuilder.like(root.get("created_by_lx"), keyword)
                                  )
                );
    }
    public static Specification<Mittaus> mittausSquareArea(MittausSearchParameters parameters) {
        if (parameters.getSquareArea() == null) return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (parameters.getOperatorForSquareArea() == null) throw new IllegalStateException("Query operator cannot be null");
        switch (parameters.getOperatorForSquareArea()) {
            case EQUALS:
                return ((root, criteriaQuery, criteriaBuilder) ->
                    parameters.getSquareArea() == null ?
                    criteriaBuilder.conjunction() :
                    criteriaBuilder.equal(root.get("rakennuksen_pinta_ala"), parameters.getSquareArea()));
            case LESS_THAN:
                return ((root, criteriaQuery, criteriaBuilder) ->
                        parameters.getSquareArea() == null ?
                                criteriaBuilder.conjunction() :
                                criteriaBuilder.lt(root.get("rakennuksen_pinta_ala"),
                                                  parameters.getSquareArea()));
            case GREATER_THAN:
                return ((root, criteriaQuery, criteriaBuilder) ->
                        parameters.getSquareArea() == null ?
                                criteriaBuilder.conjunction() :
                                criteriaBuilder.gt(root.get("rakennuksen_pinta_ala"),
                                                  parameters.getSquareArea()));
            default:
                return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction());

        }
    }

    public static Specification<Mittaus> mittausConstructionYear(MittausSearchParameters parameters) {
        /* If constructionYear  == then speficication ignored*/
        if (parameters.getConstructionYear() == null) return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction());
        /* if query operator is not given throw error */
        if (parameters.getOperatorForConstructionYear() == null) throw new IllegalStateException("query operator cannot be " +
                                                                                      "null)");
        switch (parameters.getOperatorForConstructionYear()) {
            case EQUALS:
                return ((root, criteriaQuery, criteriaBuilder) ->
                        parameters.getConstructionYear() == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("rakennusvuosi"), parameters.getConstructionYear()));
            case LESS_THAN:
                return ((root, criteriaQuery, criteriaBuilder) ->
                        parameters.getConstructionYear() == null ?
                                criteriaBuilder.conjunction() :
                                criteriaBuilder.lt(root.get("rakennusvuosi"),
                                                  parameters.getConstructionYear()));
            case GREATER_THAN:
                return ((root, criteriaQuery, criteriaBuilder) ->
                        parameters.getConstructionYear() == null ?
                                criteriaBuilder.conjunction() :
                                criteriaBuilder.gt(root.get("rakennusvuosi"),
                                                  parameters.getConstructionYear()));
            default:
                return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction());
        }
    }
}
