package g56133.atl.stib.model.core;

import g56133.atl.stib.model.config.ConfigManager;
import static g56133.atl.stib.model.core.Request.*;
import g56133.atl.stib.model.dto.FavoriteDto;
import g56133.atl.stib.model.dto.StationDto;
import g56133.atl.stib.model.dto.StopDto;
import g56133.atl.stib.model.exception.RepositoryException;
import g56133.atl.stib.model.repository.FavoriteRepository;
import g56133.atl.stib.model.repository.StationRepository;
import g56133.atl.stib.model.repository.StopRepository;
import g56133.atl.stib.utils.Observable;
import g56133.atl.stib.utils.Observer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Leong Paeg-Hing
 */
public class Facade implements Model, Observable {

    private DijkstraAlgorithm dka;
    private Graph graph;
    private List<Observer> observers;

    public Facade() throws IOException {
        dka = new DijkstraAlgorithm();
        observers = new ArrayList<>();
        graph = new Graph();
        try {
            ConfigManager.getInstance().load();
            graphCreation();
        } catch (RepositoryException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void search(String origin, String destination) throws RepositoryException {
        /**
         * Checking
         */
        if(origin == null || destination == null) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose an origin and a destination.");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }
        
        /**
         * Get data
         */
        StationRepository repository = new StationRepository();
        StationDto o = repository.get(origin);
        StationDto d = repository.get(destination);
        
        search(o.getKey(), d.getKey());
    }
    
    @Override
    public void search(int origin, int destination) throws RepositoryException {
        Node no = this.graph.getNode(origin);
        this.dka.calculateShortestPathFromSource(no);
        List<Object> object = new ArrayList<>();
        object.add(this.graph.getNode(destination));
        object.add(SEARCH);
        notifyObserver(object);
    }
    
    @Override
    public void applyFavorite(String favorite) throws RepositoryException {
        
        /**
         * Checking
         */
        if(favorite == null) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose a favorite.");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }
        
        FavoriteRepository favRep = new FavoriteRepository();
        FavoriteDto fav = favRep.get(favorite);
        
        StationRepository staionRep = new StationRepository();
        StationDto o = staionRep.get(fav.getOrigin());
        StationDto d = staionRep.get(fav.getDestintion());
        List<String> favStations = new ArrayList<>();
        favStations.add(o.getName());
        favStations.add(d.getName());
        
        List<Object> object = new ArrayList<>();
        object.add(favStations);
        object.add(FAV);
        notifyObserver(object);
    }
    
    @Override
    public void addFavorite(String name, String origin, String destination) throws RepositoryException {
        /**
         * Checking
         */
        if(origin == null || destination == null) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose an origin and a destination.");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }else if(name.length() == 0) {
            List<Object> object = new ArrayList<>();
            object.add("You must enter name");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }
        
        FavoriteRepository favRep = new FavoriteRepository();
        String nameUpper = name.toUpperCase();    
        FavoriteDto checkFav = favRep.get(nameUpper);
        
        /**
         * Checking
         */
        if(checkFav != null) {
            List<Object> object = new ArrayList<>();
            object.add("A favorite with this name already exist");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }
        
        StationRepository staionRep = new StationRepository();
        StationDto o = staionRep.get(origin);
        StationDto d = staionRep.get(destination);
        
        /**
         * Insert and update view
         */
        FavoriteDto dto = new FavoriteDto(nameUpper, o.getKey(), d.getKey());
        favRep.insert(dto);
        List<Object> object = new ArrayList<>();
        object.add(nameUpper);
        object.add(ADD);
        notifyObserver(object);
    }
    
    @Override
    public void deleteFavorite(String name) throws RepositoryException {
        
        if(name == null) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose a favorite.");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }
        
        FavoriteRepository favRep = new FavoriteRepository();
        String nameUpper = name.toUpperCase();
        favRep.delete(nameUpper);
        
        /**
         * Send the update list
         */
        List<String> fav = new ArrayList<>();
        List<FavoriteDto> dtos = favRep.getAll();
        for (FavoriteDto favorite : dtos) {
            fav.add(favorite.getKey());
        }
        List<Object> object = new ArrayList<>();
        object.add(fav);
        object.add(DELETE);
        notifyObserver(object);
    }
    
    @Override
    public void changeFavoName(String oldName, String newName) throws RepositoryException {
        
        /**
         * Checking
         */
        if(oldName == null) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose a favorite");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }
        
        String newNameUpper = newName.toUpperCase();
        if(oldName.equalsIgnoreCase(newName)) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose a differente name.");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }else if(newName.length() == 0) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose enter name.");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }
        
        /**
         * Checking
         */
        FavoriteRepository favRep = new FavoriteRepository();
        FavoriteDto checkFav = favRep.get(newNameUpper);
        if(checkFav != null) {
            List<Object> object = new ArrayList<>();
            object.add("A favorite with this name already exist.");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }
        
        /**
         * Update the new name of the favorite.
         */
        FavoriteDto fav = favRep.get(oldName);
        FavoriteDto newFav = new FavoriteDto(newNameUpper, fav.getOrigin(), fav.getDestintion());
        favRep.delete(oldName);
        favRep.insert(newFav);
        
        /**
         * Send the update list of favorite
         */
        List<String> updateFav = new ArrayList<>();
        List<FavoriteDto> dtos = favRep.getAll();
        for (FavoriteDto favorite : dtos) {
            updateFav.add(favorite.getKey());
        }
        List<Object> object = new ArrayList<>();
        object.add(updateFav);
        object.add(NAMECHANGE);
        notifyObserver(object);
    }
    
    @Override
    public void changeFavoStations(String name, String origin, String destination) throws RepositoryException {
        /**
         * Checking
         */
        if(name == null) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose a favorite.");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }else if(origin == null) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose an origin");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }else if(destination == null) {
            List<Object> object = new ArrayList<>();
            object.add("You must choose a destination");
            object.add(ERROR);
            notifyObserver(object);
            return;
        }
        
        /**
         * Update the favorite
         */
        FavoriteRepository favRep = new FavoriteRepository();
        StationRepository staionRep = new StationRepository();
        StationDto o = staionRep.get(origin);
        StationDto d = staionRep.get(destination);
        
        FavoriteDto updateDto = new FavoriteDto(name, o.getKey(), d.getKey());
        favRep.insert(updateDto);
    }

    public void graphCreation() throws RepositoryException {
        Map<Integer, List<Node>> line = new HashMap<>();
        StopRepository repository = new StopRepository();
        List<StopDto> dtos = repository.getAllWithName();
        // dto pair --> key = line, value = station
        for (StopDto dto : dtos) {
            //Check if the line exist. If not create a new entry in the map
            if (!line.containsKey(dto.getKey().getKey())) {
                line.put(dto.getKey().getKey(), new ArrayList<>());
            }

            // Check if the node has already been created
            // If not create the node and put it in the list of nodes/station (nodes)
            // else add the current line to the node
            Node node;
            if (!graph.contain(dto.getKey().getValue())) {
                node = new Node(dto.getName(), dto.getKey().getKey());
                graph.addNode(dto.getKey().getValue(), node);
            } else {
                node = graph.getNode(dto.getKey().getValue());
                node.addLine(dto.getKey().getKey());
            }

            List<Node> nd = line.get(dto.getKey().getKey());
            nd.add(node); // Add the station in the line

            if (nd.size() > 1) {
                // The current station add the previous station in the list of adjacent staion
                nd.get(nd.size() - 1).addDestination(nd.get(nd.size() - 2), 1);
                // The previous station add the current station in the list of adjacent staion
                nd.get(nd.size() - 2).addDestination(nd.get(nd.size() - 1), 1);
            }
        }
    }

    @Override
    public List<String> getStationName() {
        List<String> name = new ArrayList<>();
        try {
            StationRepository repository = new StationRepository();
            List<StationDto> dtos = repository.getAll();
            for (StationDto station : dtos) {
                name.add(station.getName());
            }
        } catch (RepositoryException ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }

    @Override
    public List<String> getFavoris() {
        List<String> fav = new ArrayList<>();
        try {
            FavoriteRepository repository= new FavoriteRepository();
            List<FavoriteDto> dtos = repository.getAll();
            for (FavoriteDto favorite : dtos) {
                fav.add(favorite.getKey());
            }
        } catch (RepositoryException ex) {
            System.out.println(ex.getMessage());
        }
        return fav;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObserver(List<Object> object) {
        for(Observer o : observers) {
            o.update(object);
        }
    }

}
