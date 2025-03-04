import React from 'react';
import {
    Box,
    Flex,
    HStack,
    Link,
    IconButton,
    useDisclosure,
    useColorModeValue,
    Stack,
    useColorMode,
    Text
} from '@chakra-ui/react';
import { HamburgerIcon, CloseIcon, MoonIcon, SunIcon } from '@chakra-ui/icons';
import { Link as RouterLink } from 'react-router-dom';

const NAVIGATION_ITEMS = [
    { name: 'Spieler', path: '/spieler' },
    { name: 'Mannschaften', path: '/mannschaften' },
    { name: 'Ligen', path: '/ligen' },
    { name: 'Formationen', path: '/formationen' },
    { name: 'Mannschaftsverwaltung', path: '/club-management' },
] as const;

interface NavLinkProps {
    children: React.ReactNode;
    to: string;
}

const NavLink: React.FC<NavLinkProps> = ({ children, to }) => {
    const hoverBg = useColorModeValue('gray.200', 'gray.700');
    
    return (
        <Link
            as={RouterLink}
            px={2}
            py={1}
            rounded="md"
            _hover={{
                textDecoration: 'none',
                bg: hoverBg,
            }}
            to={to}
        >
            {children}
        </Link>
    );
};

const Navbar: React.FC = () => {
    const { isOpen, onOpen, onClose } = useDisclosure();
    const { colorMode, toggleColorMode } = useColorMode();
    
    const bg = useColorModeValue('white', 'gray.800');

    return (
        <Box bg={bg} px={4} boxShadow="sm">
            <Flex h={16} alignItems="center" justifyContent="space-between">
                <IconButton
                    size="md"
                    icon={isOpen ? <CloseIcon /> : <HamburgerIcon />}
                    aria-label="Toggle Navigation"
                    display={{ base: 'flex', md: 'none' }}
                    onClick={isOpen ? onClose : onOpen}
                />
                
                <HStack spacing={8} alignItems="center">
                    <Text fontWeight="bold" fontSize="lg">Sportverein</Text>
                    <HStack as="nav" spacing={4} display={{ base: 'none', md: 'flex' }}>
                        {NAVIGATION_ITEMS.map((link) => (
                            <NavLink key={link.path} to={link.path}>
                                {link.name}
                            </NavLink>
                        ))}
                    </HStack>
                </HStack>

                <IconButton
                    aria-label="Toggle color mode"
                    icon={colorMode === 'light' ? <MoonIcon /> : <SunIcon />}
                    onClick={toggleColorMode}
                    size="md"
                    variant="ghost"
                />
            </Flex>

            {/* Mobile Navigation Menu */}
            {isOpen && (
                <Box pb={4} display={{ base: 'block', md: 'none' }}>
                    <Stack as="nav" spacing={4}>
                        {NAVIGATION_ITEMS.map((link) => (
                            <NavLink key={link.path} to={link.path}>
                                {link.name}
                            </NavLink>
                        ))}
                    </Stack>
                </Box>
            )}
        </Box>
    );
};

export default Navbar;
