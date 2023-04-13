package com.infy.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.user.dto.BuyerDTO;
import com.infy.user.dto.CartDTO;
import com.infy.user.dto.SellerDTO;
import com.infy.user.dto.WishlistDTO;
import com.infy.user.entity.Buyer;
import com.infy.user.entity.Cart;
import com.infy.user.entity.Seller;
import com.infy.user.entity.Wishlist;
import com.infy.user.exception.UserMsException;
import com.infy.user.repository.BuyerRepository;
import com.infy.user.repository.CartRepository;
import com.infy.user.repository.SellerRepository;
import com.infy.user.repository.WishlistRepository;
import com.infy.user.utility.CustomPK;
import com.infy.user.validator.UserValidator;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static int b;
	private static int s;

	static {
		b = 100;
		s = 100;
	}

	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private CartRepository cartRepository;

	// =====================================================BUYER================================================//

	@Override
	public String buyerRegistration(BuyerDTO buyerDTO) throws UserMsException {

		UserValidator.validateBuyer(buyerDTO);

		Buyer buy = buyerRepository.findByPhoneNumber(buyerDTO.getPhoneNumber());

		if (buy != null)
			throw new UserMsException("Buyer already present");

		String id = "B" + b++;

		buy = buyerDTO.convertToEntity(id);

		buy.setIsActive(false);
		buy.setIsPrivileged(false);
		buy.setRewardPoints(0);

		buyerRepository.save(buy);

		return buy.getBuyerId();
	}

	@Override
	public String buyerLogin(String email, String password) throws UserMsException {

		if (!UserValidator.validateEmail(email))
			throw new UserMsException("Enter valid email id");

		Buyer buyer = buyerRepository.findByEmail(email);

//		System.out.println(buyer);

		if (buyer == null)
			throw new UserMsException("Wrong credentials");

		if (!buyer.getPassword().equals(password))
			throw new UserMsException("Wrong credentials");

		buyer.setIsActive(true);

		buyerRepository.save(buyer);

		return "Login Success";
	}

	@Override
	public BuyerDTO getBuyer(String id) throws UserMsException {
		Buyer buyer = buyerRepository.findByBuyerId(id);
		if(buyer == null)
			throw new UserMsException("Buyer not Found");
		return buyer.convertToDTO();

	}

	@Override
	public String deactivateBuyer(String id) throws UserMsException {
		Buyer buyer = buyerRepository.findByBuyerId(id);

		if (buyer == null) {
			throw new UserMsException("User Not Found");
		}
		buyer.setIsActive(false);
		buyerRepository.save(buyer);

		return "Account successfully Deactivated";
	}

	@Override
	public String deleteBuyer(String id) throws UserMsException {

		Buyer buyer = buyerRepository.findByBuyerId(id);

		if (buyer == null) {
			throw new UserMsException("User Not Found");
		}
		buyerRepository.delete(buyer);

		return "Account successfully deleted";
	}

	@Override
	public String updateRewardPoint(String buyerId, Integer rewPoints) throws UserMsException {

		Buyer buyer = buyerRepository.findByBuyerId(buyerId);

		if (buyer == null)
			throw new UserMsException("Buyer not found");

		buyer.setRewardPoints(rewPoints);

		buyerRepository.save(buyer);

		return "Reward Points Updated for buyer Id : " + buyer.getBuyerId();
	}

	// ============================================================SELLER====================================================//

	@Override
	public String sellerRegistration(SellerDTO sellerDTO) throws UserMsException {
		// TODO Auto-generated method stub

		UserValidator.validateSeller(sellerDTO);

		Seller seller = sellerRepository.findByPhoneNumber(sellerDTO.getPhoneNumber());

		if (seller != null)
			throw new UserMsException("Seller Already present");

		String id = "S" + s++;

		seller = new Seller();

		seller.setEmail(sellerDTO.getEmail());
		seller.setSellerId(id);
		seller.setName(sellerDTO.getName());
		seller.setPassword(sellerDTO.getPassword());
		seller.setIsActive("False");
		seller.setPhoneNumber(sellerDTO.getPhoneNumber());

		sellerRepository.save(seller);

		return seller.getSellerId();
	}

	@Override
	public String sellerLogin(String email, String password) throws UserMsException {

		if (!UserValidator.validateEmail(email))
			throw new UserMsException("Enter valid email id");

		Seller seller = sellerRepository.findByEmail(email);

		if (seller == null)
			throw new UserMsException("Wrong credentials");

		if (!seller.getPassword().equals(password))
			throw new UserMsException("Wrong credentials");

		seller.setIsActive("True");

		sellerRepository.save(seller);

		return "Login Success";
	}

	@Override
	public SellerDTO getSeller(String id) throws UserMsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSeller(String id) {

		Seller seller = sellerRepository.findBySellerId(id);

		sellerRepository.delete(seller);

		return "Account successfully deleted";
	}

	// ============================================================WISH LIST====================================================//

	@Override
	public String addWishlist(String prodId, String buyerId) {

		CustomPK cust = new CustomPK(prodId, buyerId);

		Wishlist w = new Wishlist();

		w.setCustomId(cust);

		wishlistRepository.save(w);

		return "Added Successfully to Wishlist";
	}
	
	@Override
	public List<WishlistDTO> getWishlist() throws UserMsException {
		
		Iterable<Wishlist> wishlist = wishlistRepository.findAll();
		List<WishlistDTO> list = new ArrayList<>();
		
		
		wishlist.forEach(item->{
			list.add(item.toDTO());
		});
		if(list.isEmpty())
			throw new UserMsException("No Item in wishlist");
		return list;
	}
	
	@Override
	public String deleteWishlist(WishlistDTO dto) throws UserMsException{
		wishlistRepository.delete(dto.toEntity());
		return "Deleted succesfully";
	}
	

	// ============================================================CART SERVICE====================================================//

	
	@Override
	public String cartService(String prodId, String buyerId, Integer quantity) {

		CustomPK cust = new CustomPK(prodId, buyerId);

		Cart cart = new Cart();

		cart.setCustomPK(cust);

		cart.setQuantity(quantity);

		cartRepository.save(cart);

		return "Added Successfully to Cart";
	}

	@Override
	public List<CartDTO> getCartProducts(String id) throws UserMsException {

		List<Cart> cartItem = cartRepository.findByCustomPKBuyerId(id);

		if (cartItem.isEmpty())
			throw new UserMsException("No Items In Cart");

		List<CartDTO> list = new ArrayList<>();

		for (Cart cart : cartItem) {
			CartDTO cartDTO = new CartDTO();

			cartDTO.setBuyerId(cart.getCustomPK().getBuyerId());
			cartDTO.setProdId(cart.getCustomPK().getProdId());
			cartDTO.setQuantity(cart.getQuantity());

			list.add(cartDTO);
		}

		return list;
	}

	@Override
	public String removeFromCart(String buyerId, String prodId) throws UserMsException {

		Cart cart = cartRepository.findByCustomPKBuyerIdAndCustomPKProdId(buyerId, prodId);

		if (cart == null)
			throw new UserMsException("No Such Item In Cart");

		cartRepository.deleteByCustomPKBuyerIdAndCustomPKProdId(buyerId, prodId);

		return "The product was deleted successfully";
	}

}