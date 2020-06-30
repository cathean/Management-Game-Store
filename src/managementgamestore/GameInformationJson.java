package managementgamestore;

/**
 *
 * @author ivans
 */
class ResultGameInformation {
    String title;
    String releaseDate;
    String description;
    String[] genre;
    String image;
    int score;
    String developer;
    String[] publisher;
    String Rating;
    String[] alsoAvailableOn;
}

class GameInformationJson {
    String query;
    float executionTime;
    ResultGameInformation result;
}
