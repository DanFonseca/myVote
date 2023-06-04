package com.br.myvote.myvote.business.fixture;

import com.br.myvote.myvote.business.dto.AssociateDTO;
import com.br.myvote.myvote.data.entity.Associate;

public class AssociateFixture {

    public static Associate createAssociate() {
        Associate associate =  new Associate();
        associate.setCpf("12345678");
        associate.setName("The Associate");
        associate.setEmail("associatE@myvote.com");

        return associate;
    }

    public static AssociateDTO createAssociateDTO(){
        return new AssociateDTO(createAssociate());
    }

}
