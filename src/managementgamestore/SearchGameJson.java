package managementgamestore;

import java.awt.List;

/**
 *
 * @author ivans
 */
class ResultSearchGame {
    String title;
    String platform;
}

class SearchGameJson {
    String query;
    float executionTime;
    ResultSearchGame[] result;
}
