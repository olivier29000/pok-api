package ol.pokwebservice.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import ol.pokwebservice.objects.Carte;
import ol.pokwebservice.objects.Combinaison;
import ol.pokwebservice.objects.Main;
import ol.pokwebservice.objects.enums.SigneCarte;
import ol.pokwebservice.objects.enums.ValeurCarte;
import ol.pokwebservice.repositories.CarteRepository;

public class CartesUtils {
	
	
	
	public static List<Carte> trierCartesParValeur(List<Carte> listeCartes){
		List<Carte> listeCartesRetour = listeCartes.stream()
										.sorted(
											(carte1, carte2) -> carte1.getValeurCarte().compareTo(carte2.getValeurCarte()))
										.collect(Collectors.toList());
		return listeCartesRetour;
	}
	
	public static List<Carte> supprimerCartes(List<Carte> listeCartes, List<Carte> cartesASupprimer){
		
		for (Carte carteASupprimer : cartesASupprimer) {
			listeCartes = listeCartes.stream()
					.filter(carte -> !carte.equals(carteASupprimer))
					.collect(Collectors.toList());
		}
		
		return listeCartes;
	}
	
	public static List<Carte> obtenirOccurenceMax(List<Carte> listeCartes){
		
		List<Carte> listeCartesRetour = new ArrayList<Carte>();
		List<Carte> listeCartesTempo = new ArrayList<Carte>();
		for (ValeurCarte valeurCarte : ValeurCarte.values()) {
			Stream streamCartes = listeCartes.stream();
			listeCartesTempo = (List<Carte>) streamCartes
												.filter(carte -> ((Carte) carte).getValeurCarte().equals(valeurCarte))
												.map(x -> x)
												.collect(Collectors.toList());
			if (listeCartesTempo.size() > 1 
					&& (listeCartesTempo.size() > listeCartesRetour.size() 
					|| listeCartesTempo.size() == listeCartesRetour.size()
					&& listeCartesTempo.get(0).getValeurCarte().compareTo(listeCartesRetour.get(0).getValeurCarte()) > 0 )) {
				listeCartesRetour = listeCartesTempo;
			}
		}
		
		return listeCartesRetour;
	}
	
	
	
	public static Carte getCarteMax(List<Carte> cartes) {
		Optional<Carte> carteOpt = 
				cartes.stream().max((carte1,carte2) -> carte1.getValeurCarte().compareTo(carte2.getValeurCarte()));
		if (carteOpt.isPresent()) {
			return carteOpt.get();
		}
		return null;	 
	}
	
	public static Carte getCarteMin(List<Carte> cartes) {
		Optional<Carte> carteOpt = 
				cartes.stream().max((carte1,carte2) -> carte2.getValeurCarte().compareTo(carte1.getValeurCarte()));
		if (carteOpt.isPresent()) {
			return carteOpt.get();
		}
		return null;
		 
	}
	
	public static Carte instancierCarte (String nomCarte) {
		ValeurCarte valeurCarte = null;
		if (nomCarte.startsWith("as")) {
			valeurCarte = ValeurCarte.AS;
		}else if (nomCarte.startsWith("roi")) {
			valeurCarte = ValeurCarte.ROI;
		}else if (nomCarte.startsWith("dame")) {
			valeurCarte = ValeurCarte.DAME;
		}else if (nomCarte.startsWith("valet")) {
			valeurCarte = ValeurCarte.VALET;
		}else if (nomCarte.startsWith("dix")) {
			valeurCarte = ValeurCarte.DIX;
		}else if (nomCarte.startsWith("neuf")) {
			valeurCarte = ValeurCarte.NEUF;
		}else if (nomCarte.startsWith("huit")) {
			valeurCarte = ValeurCarte.HUIT;
		}else if (nomCarte.startsWith("sept")) {
			valeurCarte = ValeurCarte.SEPT;
		}else if (nomCarte.startsWith("six")) {
			valeurCarte = ValeurCarte.SIX;
		}else if (nomCarte.startsWith("cinq")) {
			valeurCarte = ValeurCarte.CINQ;
		}else if (nomCarte.startsWith("quatre")) {
			valeurCarte = ValeurCarte.QUATRE;
		}else if (nomCarte.startsWith("trois")) {
			valeurCarte = ValeurCarte.TROIS;
		}else if (nomCarte.startsWith("deux")) {
			valeurCarte = ValeurCarte.DEUX;
		}
		SigneCarte signeCarte = null;
				if (nomCarte.endsWith("Ca")) {
					signeCarte = SigneCarte.CARREAU;
				}else if (nomCarte.endsWith("Co") ){
					signeCarte = SigneCarte.COEUR;
				}else if (nomCarte.endsWith("Pi")) {
					signeCarte = SigneCarte.PIQUE;
				}else if (nomCarte.endsWith("Tr")) {
					signeCarte = SigneCarte.TREFLE;
				}
				
		return new Carte(valeurCarte , signeCarte);
		
		
		}

	
	
	
	
	
	
	
	

}
